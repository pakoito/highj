package org.highj.data.transformer.state;

import org.highj._;
import org.highj.__;
import org.highj.___;
import org.highj.data.transformer.StateT;
import org.highj.data.tuple.T2;
import org.highj.typeclass1.monad.Applicative;
import org.highj.typeclass1.monad.Monad;

/**
 * @author Cinton Selke
 */
public interface StateTApplicative<S, M> extends StateTApply<S, M>, Applicative<_<_<StateT.µ, S>, M>> {

    public Monad<M> m();

    @Override
    public default <A> StateT<S, M, A> pure(A a) {
        return (S s) -> m().pure(T2.of(a, s));
    }
}