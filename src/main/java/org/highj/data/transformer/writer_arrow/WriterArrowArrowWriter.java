/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.highj.data.transformer.writer_arrow;

import org.highj._;
import org.highj.__;
import org.highj.___;
import org.highj.____;
import org.highj.data.transformer.WriterArrow;
import org.highj.data.tuple.T0;
import org.highj.data.tuple.T2;
import org.highj.typeclass2.arrow.ArrowWriter;

/**
 *
 * @author clintonselke
 */
public interface WriterArrowArrowWriter<W,A> extends WriterArrowArrow<W,A>, ArrowWriter<W,_<_<WriterArrow.µ,W>,A>> {

    @Override
    public default WriterArrow<W, A, W, T0> write() {
        return WriterArrow.writerArrow(
            a().arr((W x) -> T2.of(T0.of(), x))
        );
    }

    @Override
    public default <B, C> WriterArrow<W, A, B, T2<C, W>> newWriter(__<_<_<WriterArrow.µ, W>, A>, B, C> a) {
        return WriterArrow.writerArrow(
            a().dot(
                a().arr((T2<C,W> x) -> T2.of(T2.of(x._1(), x._2()), x._2())),
                WriterArrow.narrow(a).run()
            )
        );
    }
}
