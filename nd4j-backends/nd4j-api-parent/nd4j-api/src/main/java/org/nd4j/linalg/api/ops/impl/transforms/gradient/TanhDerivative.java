package org.nd4j.linalg.api.ops.impl.transforms.gradient;


import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.imports.NoOpNameFoundException;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.BaseGradientOp;
import org.nd4j.linalg.api.ops.impl.transforms.Tanh;
import org.nd4j.linalg.factory.Nd4j;

import java.util.Collections;
import java.util.List;

/**
 *
 */
public class TanhDerivative extends BaseGradientOp  {
    public TanhDerivative(SameDiff sameDiff, SDVariable i_v1, SDVariable i_v2) {
        super(sameDiff, i_v1, i_v2);
    }

    public TanhDerivative(SameDiff sameDiff, SDVariable i_v1, SDVariable i_v2, boolean inPlace) {
        super(sameDiff, i_v1, i_v2, inPlace);
    }

    public TanhDerivative(INDArray x, INDArray z) {
        super(x, z);
    }

    public TanhDerivative() {
    }

    public TanhDerivative(INDArray x, INDArray z, long n) {
        super(x, z, n);
    }

    public TanhDerivative(INDArray x, INDArray y, INDArray z) {
        super(x, y, z, z.lengthLong());
    }

    public TanhDerivative(INDArray x) {
        super(x);
    }

    /**
     * An op number
     *
     * @return
     */
    @Override
    public int opNum() {
        return 0;
    }

    /**
     * The opName of this operation
     *
     * @return the opName of this operation
     */
    @Override
    public String opName() {
        return "tanhderivative";
    }


    @Override
    public String onnxName() {
        throw new NoOpNameFoundException("No onnx op opName found for " +  opName());
    }

    @Override
    public String tensorflowName() {
        throw new NoOpNameFoundException("No tensorflow op opName found for " +  opName());
    }

    @Override
    public void exec() {
        Nd4j.getExecutioner().exec(new org.nd4j.linalg.api.ops.impl.transforms.TanhDerivative(x,z));
        z.muli(wrt());
    }

    @Override
    public void exec(int... dimensions) {
        super.exec(dimensions);
    }

    @Override
    public List<SDVariable> doDiff(List<SDVariable> i_v) {
        SDVariable ret = f().div(f().one(outputVariables()[0].getShape()),f().pow(f().cosh(arg()),2));
        return Collections.singletonList(ret);
    }

}
