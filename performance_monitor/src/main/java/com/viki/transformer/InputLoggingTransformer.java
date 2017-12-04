package com.viki.transformer;

import com.viki.annotation.InputLog;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.bytecode.AccessFlag;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/11/7 11:35
 */
public class InputLoggingTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        try {
            CtClass cc = ClassPool.getDefault().get(className);
            CtClass loggerClass = ClassPool.getDefault().get("org.slf4j.Logger");
            CtField logger = null;
            try{
                logger = cc.getField("logger");
            }catch (Exception e){
            }
            if(logger == null){
                cc.addField(new CtField(loggerClass, "logger", cc), CtField.Initializer.byExpr("org.slf4j.LoggerFactory.getLogger(this.getClass().getName());"));
            }
            for(CtMethod method : cc.getDeclaredMethods()){
                if((method.getModifiers() & AccessFlag.STATIC) == AccessFlag.STATIC){
                    continue;
                }
                Object[][] annosInParams = method.getAvailableParameterAnnotations();
                for(Object[] annos : annosInParams){
                    if(annos != null && annos.length > 0){
                        for(Object anno : annos){
                            if(anno instanceof InputLog){
//
                            }
                        }
                    }
                }
            }
            return cc.toBytecode();
        } catch (Exception e) {
        }
        return new byte[0];
    }
}
