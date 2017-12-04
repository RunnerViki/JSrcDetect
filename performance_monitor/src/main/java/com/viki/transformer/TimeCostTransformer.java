package com.viki.transformer;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.Descriptor;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Arrays;

/**
 * Function: 运行时长监控
 *
 * @author Viki
 * @date 2017/11/6 16:20
 */
public class TimeCostTransformer implements ClassFileTransformer{

    private String monitorPackage;

    private long timeElapseThreshold = 20;

    public TimeCostTransformer(String monitorPackage){
        this.monitorPackage = monitorPackage;
    }

    public TimeCostTransformer(String monitorPackage, Long timeElapseThreshold){
        this(monitorPackage);
        if(timeElapseThreshold != null){
            this.timeElapseThreshold = timeElapseThreshold;
        }
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if(null == className){
            return null;
        }
        try {
            className = className.replace("/", ".");
            CtClass cc = ClassPool.getDefault().get(className);
            if(null == monitorPackage || !className.startsWith(monitorPackage)){
                return cc.toBytecode();
            }
            Arrays.stream(cc.getDeclaredMethods()).filter(method -> (method.getModifiers() & AccessFlag.STATIC) != AccessFlag.STATIC && (method.getModifiers() & AccessFlag.ABSTRACT) != AccessFlag.ABSTRACT).forEach(
                method -> {
                    try {
                        method.addLocalVariable("startTime", CtClass.longType);
                        method.insertBefore("startTime = System.currentTimeMillis();\n");
                        method.insertAfter("" +
                                "long elapse = (System.currentTimeMillis() - startTime);\n" +
                                "if(elapse > "+timeElapseThreshold+"){" +
                                "   com.viki.utils.TimeCostRecorder.addRecord(\""+method.getLongName() + Descriptor.toString(method.getSignature())+"\",elapse);" +
                                "}");
                    } catch (CannotCompileException e) {
                        e.printStackTrace();
                    }
                }
            );
            return cc.toBytecode();
        } catch (Exception e) {
        }
        return null;
    }
}
