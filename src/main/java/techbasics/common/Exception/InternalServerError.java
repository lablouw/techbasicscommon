package techbasics.common.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InternalServerError extends Throwable {

    private String errorTicket;

}
