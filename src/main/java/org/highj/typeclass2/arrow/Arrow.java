package org.highj.typeclass2.arrow;

import org.derive4j.hkt.__2;
import org.highj.data.tuple.T2;

import java.util.function.Function;

public interface Arrow<A> extends Category<A> {

    public <B, C> __2<A, B, C> arr(Function<B, C> fn);

    public <B, C, D> __2<A, T2<B, D>, T2<C, D>> first(__2<A, B, C> arrow);

    public default <B, C, D> __2<A, T2<D, B>, T2<D, C>> second(__2<A, B, C> arrow) {
        __2<A, T2<D, B>, T2<B, D>> swapForth = arr(T2<D, B>::swap);
        __2<A, T2<B, D>, T2<C, D>> arrowFirst = first(arrow);
        __2<A, T2<C, D>, T2<D, C>> swapBack = arr(T2<C, D>::swap);
        return then(swapForth, arrowFirst, swapBack);
    }

    //(***)
    public default <B, C, BB, CC> __2<A, T2<B, BB>, T2<C, CC>> split(__2<A, B, C> arr1, __2<A, BB, CC> arr2) {
        __2<A, T2<B, BB>, T2<C, BB>> one = first(arr1);
        __2<A, T2<C, BB>, T2<C, CC>> two = second(arr2);
        return then(one, two);
    }

    //(&&&)
    public default <B, C, D> __2<A, B, T2<C, D>> fanout(__2<A, B, C> arr1, __2<A, B, D> arr2) {
        __2<A, B, T2<B, B>> duplicated = arr((B a) -> T2.<B, B>of(a, a));
        __2<A, T2<B, B>, T2<C, D>> splitted = split(arr1, arr2);
        return then(duplicated, splitted);
    }

    public default <B> __2<A, B, B> returnA() {
        return arr(Function.<B>identity());
    }

    //(^>>) :: Arrow a => (b -> c) -> a c d -> a b d
    public default <B, C, D> Function<__2<A, C, D>, __2<A, B, D>> precomposition(Function<B, C> fn) {
        //f ^>> a = arr f >>> a
        return bc -> then(arr(fn), bc);
    }

    //(^<<) :: Arrow a => (c -> d) -> a b c -> a b d
    public default <B, C, D> Function<__2<A, B, C>, __2<A, B, D>> postcomposition(final Function<C, D> fn) {
        //f ^<< a = arr f <<< a
        return ab -> then(ab, arr(fn));
    }

    public default <X> ApplicativeFromArrow<A,X> getApplicative() {
        return () -> Arrow.this;
   }
}