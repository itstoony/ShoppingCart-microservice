package itstoony.com.github.shoppingchart.dto.record;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CartItemRecord(
        @NotNull
        @Min(1)
        Long productID,
        @NotNull
        @Min(1)
        Integer quantity
) {
}
