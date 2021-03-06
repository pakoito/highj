package org.highj.data.transformer.identity;

import org.derive4j.hkt.__;
import org.highj.data.transformer.IdentityT;
import org.highj.typeclass1.foldable.Traversable;
import org.highj.typeclass1.monad.Applicative;

import java.util.function.Function;

public interface IdentityTTraversable<M> extends Traversable<__<IdentityT.µ, M>> {

    public Traversable<M> getM();

    @Override
    public default <A, B> IdentityT<M, B> map(Function<A, B> fn, __<__<IdentityT.µ, M>, A> nestedA) {
        IdentityT<M, A> aId = IdentityT.narrow(nestedA);
        return new IdentityT<>(getM().map(fn, aId.get()));
    }

    @Override
    public default <A, X> __<X, __<__<IdentityT.µ, M>, A>> sequenceA(Applicative<X> applicative, __<__<IdentityT.µ, M>, __<X, A>> traversable) {
        IdentityT<M, __<X, A>> traversableId = IdentityT.narrow(traversable);
        __<X, __<M, A>> result = getM().sequenceA(applicative, traversableId.get());
        return applicative.map(v -> new IdentityT(v), result);
    }

}
