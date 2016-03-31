package org.highj.data.tuple.t2;

import org.highj._;
import org.highj.__;
import org.highj.data.tuple.T2;
import org.highj.typeclass1.monad.Bind;

import java.util.function.Function;

public interface T2Bind<S> extends T2Apply<S>, Bind<_<T2.µ, S>> {

    @Override
    public default <A, B> T2<S, B> bind(_<_<T2.µ, S>, A> nestedA, Function<A, _<_<T2.µ, S>, B>> fn) {
        T2<S, A> ta = T2.narrow(nestedA);
        T2<S, B> tb = T2.narrow(fn.apply(ta._2()));
        return T2.of(getS().apply(ta._1(), tb._1()), tb._2());
    }
}
