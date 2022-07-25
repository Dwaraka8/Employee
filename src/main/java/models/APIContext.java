package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class APIContext {

    @Getter
    @Setter
    private static int id;

    @Getter
    @Setter
    private static String name;

    @Getter
    @Setter
    private static int id1;
}
