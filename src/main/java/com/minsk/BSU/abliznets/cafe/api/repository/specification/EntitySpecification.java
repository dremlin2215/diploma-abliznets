package com.minsk.BSU.abliznets.cafe.api.repository.specification;

public interface EntitySpecification<T> {
    boolean specified(T specifiedElement);
}
