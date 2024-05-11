package com.volodymyrvasylyshyn.helperserver.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateRangeRequest {
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate fromDate;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate toDate;
}
