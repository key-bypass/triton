package com.cutting.machine.operation.duplicateCut.cutpath;

import androidx.core.app.NotificationManagerCompat;

import com.cutting.machine.CuttingMachine;
import com.cutting.machine.ToolSizeManager;
import com.cutting.machine.bean.DestPoint;
import com.cutting.machine.bean.KeyAlign;
import com.cutting.machine.bean.StepBean;
import com.cutting.machine.clamp.Clamp;
import com.cutting.machine.clamp.ClampManager;
import com.cutting.machine.clamp.MachineData;
import com.cutting.machine.communication.OperationManager;
import com.cutting.machine.duplicate.Benchmark;
import com.cutting.machine.duplicate.SinglePathData;
import com.cutting.machine.error.ErrorCode;
import com.cutting.machine.error.ErrorHelper;
import com.cutting.machine.operation.duplicateCut.DuplicateCutParams;
import com.cutting.machine.speed.Speed;
import com.cutting.machine.utils.StepBeanFactory;
import com.cutting.machine.utils.UnitConvertUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class DuplicateDoubleSideKeyCut extends DuplicateCutPath {
    private static final float[] twiceCut = {0.4f, 0.0f};
    private static final float[] thriceCut = {0.6f, 0.2f, 0.0f};

}
