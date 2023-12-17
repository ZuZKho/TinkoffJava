package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;

public record PersonRecord(@NotNull String name, @Min(0)int id, @Min(1)@Max(100)int age, String phone) {
}
