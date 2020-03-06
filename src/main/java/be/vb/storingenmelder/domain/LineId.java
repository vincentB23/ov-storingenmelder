package be.vb.storingenmelder.domain;

import java.io.Serializable;
import java.util.Objects;

public class LineId implements Serializable {
    private int number;
    private int province;

    public LineId() {
    }

    public LineId(int number, int province) {
        this.number = number;
        this.province = province;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineId lineId = (LineId) o;
        return number == lineId.number &&
                province == lineId.province;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, province);
    }
}
