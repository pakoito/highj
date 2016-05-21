package org.highj.data.instance.maybe;

import org.derive4j.hkt.__;
import org.highj.data.Maybe;
import org.highj.typeclass1.functor.Functor;

import java.util.function.Function;

import static org.highj.data.Maybe.narrow;

public interface MaybeFunctor extends Functor<Maybe.µ> {

    default <A, B> Maybe<B> map(Function<A, B> fn, __<Maybe.µ, A> nestedA) {
        return narrow(nestedA).map(fn);
    }

}