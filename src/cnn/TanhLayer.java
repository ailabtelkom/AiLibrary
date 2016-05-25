/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cnn;

/**
 *
 * @author dee
 */
public class TanhLayer extends LayerNonLinear {

    public TanhLayer(Options opt) {
        super(opt);
        setOut_depth((int) opt.getOpt("in_depth"));
        setLayer_type("tanh");
    }

    @Override
    public Vol forward(Vol V, boolean is_training) {
        setIn_act(V);
        Vol V2 = V.cloneAndZero();
        int N = V.getW().length;
        for (int i = 0; i < N; i++) {
            V2.setW(i, Math.tanh(V.getW(i)));
        }
        setOut_act(V2);
        return getOut_act();
    }

    @Override
    public void backward() {
        Vol V = getIn_act();
        Vol V2 = getOut_act();
        int N = V.getW().length;
        V.setDw(new double[N]);
        for (int i = 0; i < N; i++) {
            double v2wi = V2.getW(i);
            V.setDw(i, (1 - v2wi * v2wi) * V2.getDw(i));
        }
    }

}
