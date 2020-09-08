package ru.eexxyyq.hermes.app.model.search.criteria;

import lombok.Getter;
import ru.eexxyyq.hermes.app.common.utils.ChecksUtils;

/**
 * @author yatixonov
 * @created 03/09/2020 - 22:54
 * @project hermes
 */

@Getter
public class RangeCriteria {
    private final int page;
    private final int rowNum;

    public RangeCriteria(final int page, final int rowNum) {
        ChecksUtils.checkParameter(page >= 0, String.format("Invalid parameter page: %d", page));
        ChecksUtils.checkParameter(rowNum >= 0, String.format("Invalid parameter rows number: %d", rowNum));

        this.page = page;
        this.rowNum = rowNum;
    }
}
