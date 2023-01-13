package practice_lld_2023.machine_coding.zoomcar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class Slot {
    Date Start;
    Date End;
}
