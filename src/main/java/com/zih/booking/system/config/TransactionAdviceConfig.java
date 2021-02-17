package com.zih.booking.system.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * @author shahy
 * @description 通过AOP切面设置全局事务，拦截service包下面所有方法
 * AOP术语：通知（Advice）、连接点（Joinpoint）、切入点（Pointcut)、切面（Aspect）、目标(Target)、代理(Proxy)、织入（Weaving）
 */
@Aspect
@Configuration
public class TransactionAdviceConfig {

    private static final int TX_METHOD_TIMEOUT = 5;

    /**
     * 定义切点变量：拦截com.zih.booking.service包下所有类的所有方法,返回值类型任意的方法
     */
    private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.zih.booking.service..*(..))";

    @Autowired
    private PlatformTransactionManager transactionManager;


    /**
     * springBoot事务配置
     */
    @Bean
    public TransactionInterceptor txAdvice() {

        /*写事务，用于增删改*/
        DefaultTransactionAttribute txAttrRequired = new DefaultTransactionAttribute();
        /*PROPAGATION_REQUIRED:事务隔离性为1，若当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。这是默认值。 */
        txAttrRequired.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        /*设置事务失效时间，如果超过5秒，则回滚事务*/
        txAttrRequired.setTimeout(TX_METHOD_TIMEOUT);
        /*只读事物、不做更新删除等*/
        DefaultTransactionAttribute txAttrRequiredReadonly = new DefaultTransactionAttribute();
        /*
         * TransactionDefinition 定义事务的隔离级别；
         * PROPAGATION_NOT_SUPPORTED事务传播级别5，以非事务运行，如果当前存在事务，则把当前事务挂起
         */
        txAttrRequiredReadonly.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
        txAttrRequiredReadonly.setReadOnly(true);

        /*事务管理规则，声明具备事务管理的方法名*/
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();

        //写
        source.addTransactionalMethod("add*", txAttrRequired);
        source.addTransactionalMethod("insert*", txAttrRequired);
        source.addTransactionalMethod("save*", txAttrRequired);
        source.addTransactionalMethod("update*", txAttrRequired);
        source.addTransactionalMethod("edit*", txAttrRequired);
        source.addTransactionalMethod("remove*", txAttrRequired);
        source.addTransactionalMethod("delete*", txAttrRequired);

        //只读
        source.addTransactionalMethod("get*", txAttrRequiredReadonly);
        source.addTransactionalMethod("query*", txAttrRequiredReadonly);
        source.addTransactionalMethod("find*", txAttrRequiredReadonly);
        source.addTransactionalMethod("select*", txAttrRequiredReadonly);
        source.addTransactionalMethod("count*", txAttrRequiredReadonly);
        source.addTransactionalMethod("list*", txAttrRequiredReadonly);
        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        /* 声明切点的面
         * 切面（Aspect）：切面就是通知和切入点的结合。通知和切入点共同定义了关于切面的全部内容——它的功能、在何时和何地完成其功能。
         * */
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        /*声明和设置需要拦截的方法,用切点语言描写*/
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        /*设置切面=切点pointcut+通知TxAdvice*/
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
