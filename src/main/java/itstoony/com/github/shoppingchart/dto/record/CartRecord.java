package itstoony.com.github.shoppingchart.dto.record;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CartRecord(
        @NotNull
        @Min(1)
        Long customerID,
        List<CartItemRecord> items
) {
}
