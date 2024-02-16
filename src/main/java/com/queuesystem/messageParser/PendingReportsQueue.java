package com.queuesystem.messageParser;

import com.queuesystem.dbAdapter.DBAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class PendingReportsQueue {
    private final Queue<TaskReport> pendingReports = new ConcurrentLinkedQueue<>();
    private final DBAdapter dbAdapter;

    @Autowired
    public PendingReportsQueue(DBAdapter dbAdapter) {
        this.dbAdapter = dbAdapter;
    }

    public void addReport(TaskReport report) {
        pendingReports.offer(report);
    }

    public void processPendingReports() {
        Iterator<TaskReport> iterator = pendingReports.iterator();
        while (iterator.hasNext()) {
            TaskReport report = iterator.next();
            if (dbAdapter.updateFinishedOrder(report)) {
                iterator.remove(); // Usuń raport z kolejki, jeśli aktualizacja się powiodła
            } else {
                break; // Przerwij procesowanie, jeśli aktualizacja nie powiodła się
            }
        }
    }
}

