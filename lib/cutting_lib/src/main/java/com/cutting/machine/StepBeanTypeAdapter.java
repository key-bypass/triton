package com.cutting.machine;

import android.util.JsonReader;
import android.util.JsonWriter;

import com.google.gson.TypeAdapter;
import com.cutting.machine.bean.StepBean;
import com.cutting.machine.clamp.MachineData;

import java.io.IOException;

/* loaded from: classes2.dex */
public class StepBeanTypeAdapter extends TypeAdapter<StepBean> {
    @Override
    public void write(com.google.gson.stream.JsonWriter out, StepBean value) throws IOException {
        out.beginObject();
        out.name("S").value(value.getS());
        out.name("T").value(value.getT());
        out.name("X").value(value.getX());
        out.name("Y").value(value.getY());
        out.name("Z").value(value.getZ());
        out.name("V").value(value.getvStr());
        out.name("Rule").value(value.getRule());
        out.name("Des").value(value.getDes());
        out.endObject();
    }

    @Override
    public StepBean read(com.google.gson.stream.JsonReader in) throws IOException {
        StepBean stepBean = new StepBean();
        in.beginObject();
        while (in.hasNext()) {
            String nextName = in.nextName();
            nextName.hashCode();
            char c = 65535;
            switch (nextName.hashCode()) {
                case 83:
                    if (nextName.equals("S")) {
                        c = 0;
                        break;
                    }
                    break;
                case 84:
                    if (nextName.equals("T")) {
                        c = 1;
                        break;
                    }
                    break;
                case 86:
                    if (nextName.equals("V")) {
                        c = 2;
                        break;
                    }
                    break;
                case 88:
                    if (nextName.equals("X")) {
                        c = 3;
                        break;
                    }
                    break;
                case 89:
                    if (nextName.equals("Y")) {
                        c = 4;
                        break;
                    }
                    break;
                case 90:
                    if (nextName.equals("Z")) {
                        c = 5;
                        break;
                    }
                    break;
                case 68594:
                    if (nextName.equals("Des")) {
                        c = 6;
                        break;
                    }
                    break;
                case 2558748:
                    if (nextName.equals("Rule")) {
                        c = 7;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    stepBean.setS(in.nextString());
                    break;
                case 1:
                    stepBean.setT(in.nextInt());
                    break;
                case 2:
                    stepBean.setvStr(in.nextString());
                    break;
                case 3:
                    stepBean.setX((int) (in.nextInt() / MachineData.dXScale));
                    break;
                case 4:
                    stepBean.setY((int) (in.nextInt() / MachineData.dYScale));
                    break;
                case 5:
                    stepBean.setZ((int) (in.nextInt() / MachineData.dZScale));
                    break;
                case 6:
                    stepBean.setDes(in.nextString());
                    break;
                case 7:
                    stepBean.setRule(in.nextString());
                    break;
            }
        }
        in.endObject();
        return stepBean;
    }
    
}
