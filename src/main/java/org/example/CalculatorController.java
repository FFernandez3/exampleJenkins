package org.example;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    private final Calculator calculator;
    private final Counter addCounter;
    private final Counter multiplyCounter;

    public CalculatorController(MeterRegistry registry) {
        this.calculator = new Calculator();
        this.addCounter = registry.counter("calculator.operations", "type", "add");
        this.multiplyCounter = registry.counter("calculator.operations", "type", "multiply");
    }

    @GetMapping("/add")
    public int add(@RequestParam("a") int a, @RequestParam("b") int b) {
        addCounter.increment();
        return calculator.add(a, b);
    }

    @GetMapping("/multiply")
    public int multiply(@RequestParam("a") int a, @RequestParam("b") int b) {
        multiplyCounter.increment();
        return calculator.multiply(a, b);
    }
}

