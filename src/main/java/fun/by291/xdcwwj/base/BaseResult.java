package fun.by291.xdcwwj.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties("d")
public class BaseResult {
    private String e;
    private String m;
}
