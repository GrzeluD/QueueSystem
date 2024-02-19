package com.queuesystem.user;

import com.queuesystem.dbAdapter.DBAdapter;
import com.queuesystem.resources.Resources;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/resources")
@Getter
@Setter
@AllArgsConstructor
@Controller
public class Service {
    private final DBAdapter dbAdapter;
    private final Resources resources;

    @PostMapping("/update")
    public String updateResource(@ModelAttribute Resources resourcesToAdd) {
        Resources newResource = dbAdapter.findResourceById();

        newResource.attachMore(resourcesToAdd);

        dbAdapter.saveResources(newResource);
        return "redirect:/service";
    }

    @PostMapping("/create")
    public String createResource(@ModelAttribute Resources resourcesToAdd) {
        dbAdapter.saveResources(resourcesToAdd);
        return "redirect:/service";
    }
}
