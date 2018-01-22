/**
 * Function:
 - 问题场景：实现新接口A兼容旧接口B
 - 保证对原来会传入B接口参数的代码无侵入
    - 创建一个新的类C，使之实现接口B
 - 实质执行者为接口A
    - 类C中组合接口A，在类C的实现中，实际交由接口A的实现类处理，相当于类C是接口A实现类的代理类，或者说再一次包装
 *
 *
 * @author Viki
 * @date 2018/1/3 9:59
 */
/**
 * <b>package-info不是平常类，其作用有三个:</b><br>
 * 1、为标注在包上Annotation提供便利；<br>
 * 2、声明包的私有类和常量；声明包内友好friendly的包常量 <br>
 * 3、提供包的整体注释说明。<br>
 */
package com.viki.other.pattern.adapter;