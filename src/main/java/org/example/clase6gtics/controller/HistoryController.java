package org.example.clase6gtics.controller;

import org.example.clase6gtics.repository.HistoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/history")
public class HistoryController {
    final HistoryRepository historyRepository;
    public HistoryController( HistoryRepository historyRepository1) {
        this.historyRepository = historyRepository1;
    }
}
