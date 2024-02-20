package com.queuesystem.messageParser;

import com.queuesystem.dbAdapter.DBAdapter;
import com.queuesystem.queue.OrdersQueue;
import com.queuesystem.queue.Task;
import com.queuesystem.request.Order;
import com.queuesystem.resources.Resources;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/messages")
@Service
@Component
@Getter
@Setter
public class MessageParser {
    private String supercomputerAddress;
    private int supercomputerPort;
    private final PendingReportsQueue pendingReportsQueue;
    private Beholder beholder;
    @Autowired
    private DBAdapter dbAdapter;

    public MessageParser() {
        this.pendingReportsQueue = new PendingReportsQueue();
    }

    public void setMediator(Beholder beholder) {
        this.beholder = beholder;
    }

    @PostMapping("/free-resources")
    public ResponseEntity<?> processFreeResourcesInfo(@RequestBody FreeResourcesInfo info) {
        Resources freeResources = new Resources(info.getCpuCount(), info.getGpuCount(), info.getRamMegabytes()); // Przetwarzanie informacji o wolnych zasobach otrzymanych od superkomputera
        Resources totalResources = DBAdapter.getTotalResources(); // Pobranie informacji o całkowitych zasobach z bazy danych
        SuperComputerResources superComputerResources = new SuperComputerResources(); // Tworzenie obiektu SuperComputerResources z wolnymi i całkowitymi zasobami
        superComputerResources.setFreeResources(freeResources);
        superComputerResources.setTotalResources(totalResources);
        beholder.popSuperComputerResources(superComputerResources); // Użycie informacji o zasobach do aktualizacji kolejki zadań
        return ResponseEntity.ok().body("Zaktualizowano informacje o wolnych zasobach i zaktualizowano kolejność zadań.");
    }


    @PostMapping("/task-report")
    public ResponseEntity<?> processTaskReport(@RequestBody TaskReport report) {
        System.out.println("Supercomputer says task completed:" + report.getTaskId());

        if (!dbAdapter.updateFinishedOrder(report)) {
            // Jeśli zapis do bazy się nie udał, dodaj raport do kolejki oczekujących
            pendingReportsQueue.addReport(report);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Coudl not send report. Report has been added to queue.");
        } else {
            // Jeśli się udało, spróbuj przetworzyć również raporty oczekujące
            pendingReportsQueue.processPendingReports();
        }
        requestFreeResourcesReport();
        return ResponseEntity.ok().build();
    }

    @Async
    public void requestTaskExecution(Task task) {
        System.out.println("Asynchronously sent to supercomputer: task id " + task.getId());
    }
    public void requestFreeResourcesReport() {
        System.out.println("Supercomputer requested for free resources report.");
    }
}
