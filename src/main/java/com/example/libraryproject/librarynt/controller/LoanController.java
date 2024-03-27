package com.example.libraryproject.librarynt.controller;

import com.example.libraryproject.librarynt.controller.dto.*;
import com.example.libraryproject.librarynt.service.LoanService;
import com.example.libraryproject.librarynt.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public List<GetLoanDto> getAllLoans(){
        return loanService.getAll();
    }

    @GetMapping("/{id}")
    public GetLoanDto getOne(@PathVariable long id){
        return loanService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<CreateLoanResponseDto> create(@RequestBody CreateLoanDto loan) {
        var newLoan =  loanService.create(loan);
        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
