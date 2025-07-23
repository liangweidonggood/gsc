package com.lwd.gsc.common.converter;

import com.lwd.gsc.common.enums.EnableStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author lwd
 */
@Converter(autoApply = true)
public class EnableStatusConverter implements AttributeConverter<EnableStatus,Integer> {
    @Override
    public Integer convertToDatabaseColumn(EnableStatus status) {
        return status == null ? null : status.getCode();
    }

    @Override
    public EnableStatus convertToEntityAttribute(Integer code) {
        return code == null ? null : EnableStatus.fromCode(code);
    }
}
