package lld_2022.zoomcar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class Slot {
    Date Start;
    Date End;
}
