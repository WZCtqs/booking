package com.zih.booking.request;

import com.zih.booking.model.ProblemFeedback;
import com.zih.booking.vo.ProblemFileVo;
import lombok.Data;

@Data
public class ProblemFeedbackRequest {
    private ProblemFeedback problemFeedback;
    private ProblemFileVo[] urls;
}
