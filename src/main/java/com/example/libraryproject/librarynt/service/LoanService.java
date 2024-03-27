package com.example.libraryproject.librarynt.service;

import com.example.libraryproject.librarynt.controller.dto.*;
import com.example.libraryproject.librarynt.infrastructure.entity.LoanEntity;
import com.example.libraryproject.librarynt.infrastructure.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<GetLoanDto> getAll() {
        var loans = loanRepository.findAll();

        return loans.stream().map((loan) -> new GetLoanDto(
                loan.getId(),
                loan.getBookEntity().getId(),
                loan.getUserEntity().getId(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getReturnDate()
        )).toList();
    }

    public  GetLoanDto getOne(Long id) {
        var loan = loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));

        return new GetLoanDto(
                loan.getId(),
                loan.getBookEntity().getId(),
                loan.getUserEntity().getId(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getReturnDate()
        );
    }

    public CreateLoanResponseDto create(CreateLoanDto loan) {
        var loanEntity = new LoanEntity();

       // loanEntity.setBookEntity(loan.getBookEntity());
       // loanEntity.setUserEntity(loan.getUserEntity());
        loanEntity.setLoanDate(loan.getLoanDate());
        loanEntity.setDueDate(loan.getDueDate());
        loanEntity.setReturnDate(loan.getReturnDate());

        var newLoan = loanRepository.save(loanEntity);

        return new CreateLoanResponseDto(
                newLoan.getId(),
                newLoan.getBookEntity().getId(),
                newLoan.getUserEntity().getId(),
                newLoan.getLoanDate(),
                newLoan.getDueDate(),
                newLoan.getReturnDate()
        );
    }

    public void delete(Long id) {
        if (!loanRepository.existsById(id)) {
            throw new RuntimeException();
        }
        loanRepository.deleteById(id);
    }
}
