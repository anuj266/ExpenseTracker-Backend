package com.spendsmart.gateway.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public Mono<Map<String, Object>> welcome() {
        Map<String, Object> response = new HashMap<>();
        response.put("application", "SpendSmart API Gateway");
        response.put("version", "1.0.0");
        response.put("status", "UP");
        response.put("port", 8080);

        Map<String, String> routes = new HashMap<>();
        routes.put("Auth Service", "/auth/**");
        routes.put("Category Service", "/api/categories/**");
        routes.put("Expense Service", "/api/expenses/**");
        routes.put("Income Service", "/api/incomes/**");
        routes.put("Budget Service", "/api/budgets/**");
        routes.put("Recurring Service", "/api/recurring/**");
        routes.put("Notification Service", "/api/notifications/**");
        routes.put("Analytics Service", "/api/analytics/**");

        response.put("available_routes", routes);

        Map<String, String> examples = new HashMap<>();
        examples.put("Login", "POST /auth/login");
        examples.put("Get Categories", "GET /api/categories/user/1");
        examples.put("Get Expenses", "GET /api/expenses/user/1");
        examples.put("Monthly Summary", "GET /api/analytics/user/1/summary/2026/4");

        response.put("example_endpoints", examples);

        return Mono.just(response);
    }

    @GetMapping("/health")
    public Mono<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("gateway", "RUNNING");
        return Mono.just(response);
    }
}