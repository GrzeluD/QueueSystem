package com.queuesystem.request;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/update/{orderId}")
    public String updateOrder(@PathVariable("orderId") Integer orderId, @ModelAttribute("order") Order order, RedirectAttributes redirectAttributes) {
        try {
            // Ustaw ID order, aby upewnić się, że aktualizujesz właściwy obiekt
            order.setRequestId(orderId);
            orderService.updateOrder(order);
            redirectAttributes.addFlashAttribute("successMessage", "Order został zaktualizowany.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Nie udało się zaktualizować Order: " + e.getMessage());
        }

        return "redirect:/console";
    }

    @PostMapping("/convertToOrder")
    public String convertRequestToOrder(@ModelAttribute Order order, RedirectAttributes redirectAttributes) {
        try {
            orderService.convertRequestToOrder(order);
            redirectAttributes.addFlashAttribute("successMessage", "Request zaakceptowany");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Coś poszło nie tak: " + e.getMessage());
        }

        return "redirect:/console";
    }
}
