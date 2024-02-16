package com.queuesystem.messageParser;

import com.queuesystem.dbAdapter.DBAdapter;
import com.queuesystem.queue.OrdersQueue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/messages")
public class MessageParser {
    private final PendingReportsQueue pendingReportsQueue;
    private final OrdersQueue ordersQueue;
    private final DBAdapter dbAdapter; // Zakładając, że DBAdapter jest komponentem Springowym

    public MessageParser(OrdersQueue ordersQueue, DBAdapter dbAdapter) {
        this.ordersQueue = ordersQueue;
        this.dbAdapter = dbAdapter;
        this.pendingReportsQueue = new PendingReportsQueue(dbAdapter);
    }

    @PostMapping("/free-resources")
    public ResponseEntity<?> processFreeResourcesInfo(@RequestBody FreeResourcesInfo info) {
        Resource freeResources = new Resource(info.getCpuCount(), info.getGpuCount(), info.getRamMegabytes()); // Przetwarzanie informacji o wolnych zasobach otrzymanych od superkomputera
        Resource totalResources = dbAdapter.getTotalResources(); // Pobranie informacji o całkowitych zasobach z bazy danych
        SuperComputerResources superComputerResources = new SuperComputerResources(); // Tworzenie obiektu SuperComputerResources z wolnymi i całkowitymi zasobami
        superComputerResources.setFreeResources(freeResources);
        superComputerResources.setTotalResources(totalResources);
        ordersQueue.pop(superComputerResources); // Użycie informacji o zasobach do aktualizacji kolejki zadań
        return ResponseEntity.ok().body("Zaktualizowano informacje o wolnych zasobach i zaktualizowano kolejność zadań.");
    }

    @PostMapping("/task-report")
    public ResponseEntity<?> processTaskReport(@RequestBody TaskReport report) {
        if (!dbAdapter.updateFinishedOrder(report)) {
            // Jeśli zapis do bazy się nie udał, dodaj raport do kolejki oczekujących
            pendingReportsQueue.addReport(report);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Nie udało się zapisać raportu, został dodany do kolejki oczekujących.");
        } else {
            // Jeśli się udało, spróbuj przetworzyć również raporty oczekujące
            pendingReportsQueue.processPendingReports();
        }
        return ResponseEntity.ok().build();
    }

}
