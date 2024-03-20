package com.example.beinttestbe.utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
public class PaginatedResponse<T> {
    private final Page<T> data;
    public int getTotalPages() {
        return data.getTotalPages();
    }

    public int getPageNumber() {
        return data.getNumber();
    }

}
