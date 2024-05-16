package edu.basejava.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Resume {

    private String uuid;

    private String fullName;

    public Resume( String fullName ) {
        this.uuid = UUID.randomUUID().toString();
        this.fullName = fullName;
    }

    // for test purposes only
    public Resume( String uuid, String fullName ) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
