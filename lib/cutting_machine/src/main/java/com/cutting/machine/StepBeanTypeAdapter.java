package com.liying.core;

import android.util.JsonReader;
import android.util.JsonWriter;

import com.liying.core.bean.StepBean;
import com.liying.core.clamp.MachineData;
import java.io.IOException;

/* loaded from: classes2.dex */
public class StepBeanTypeAdapter extends TypeAdapter<StepBean> {
    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, StepBean stepBean) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("S").value(stepBean.getS());
        jsonWriter.name("T").value(stepBean.getT());
        jsonWriter.name("X").value(stepBean.getX());
        jsonWriter.name("Y").value(stepBean.getY());
        jsonWriter.name("Z").value(stepBean.getZ());
        jsonWriter.name("V").value(stepBean.getvStr());
        jsonWriter.name("Rule").value(stepBean.getRule());
        jsonWriter.name("Des").value(stepBean.getDes());
        jsonWriter.endObject();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x001a. Please report as an issue. */
    @Override // com.google.gson.TypeAdapter
    public StepBean read(JsonReader jsonReader) throws IOException {
        StepBean stepBean = new StepBean();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
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
                    stepBean.setS(jsonReader.nextString());
                    break;
                case 1:
                    stepBean.setT(jsonReader.nextInt());
                    break;
                case 2:
                    stepBean.setvStr(jsonReader.nextString());
                    break;
                case 3:
                    stepBean.setX((int) (jsonReader.nextInt() / MachineData.dXScale));
                    break;
                case 4:
                    stepBean.setY((int) (jsonReader.nextInt() / MachineData.dYScale));
                    break;
                case 5:
                    stepBean.setZ((int) (jsonReader.nextInt() / MachineData.dZScale));
                    break;
                case 6:
                    stepBean.setDes(jsonReader.nextString());
                    break;
                case 7:
                    stepBean.setRule(jsonReader.nextString());
                    break;
            }
        }
        jsonReader.endObject();
        return stepBean;
    }
}
