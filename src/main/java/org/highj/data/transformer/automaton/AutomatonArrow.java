/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.highj.data.transformer.automaton;

import java.util.function.Function;

import org.highj._;
import org.highj.__;
import org.highj.___;
import org.highj.data.transformer.Automaton;
import org.highj.data.tuple.T2;
import org.highj.typeclass2.arrow.Arrow;

/**
 *
 * @author clintonselke
 */
public interface AutomatonArrow<A> extends AutomatonCategory<A>, Arrow<_<Automaton.µ,A>> {
    
    public Arrow<A> get();
    
    @Override
    public default <B, C> Automaton<A,B,C> arr(Function<B, C> fn) {
        return () -> get().dot(
            get().arr((C c) -> T2.of(c, arr(fn))),
            get().arr(fn)
        );
    }

    @Override
    public default <B, C, D> Automaton<A, T2<B, D>, T2<C, D>> first(__<_<Automaton.µ, A>, B, C> arrow) {
        return () -> get().dot(
            get().arr((T2<T2<C,Automaton<A,B,C>>,D> x) -> T2.of(T2.of(x._1()._1(), x._2()), first(x._1()._2()))),
            get().<B,T2<C,Automaton<A,B,C>>,D>first(Automaton.narrow(arrow).unAutomaton()) // :: a (b,d) ((c,automaton a b c),d)
        );
    }
}
