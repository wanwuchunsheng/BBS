###任务规划

PC服务未完成：
   1：webSocket： 小程序与手机端通信，订单来了，通过通信接口推向PC服务端
   2：图标基础管理：增删改查（有图标上传功能）
 
/**增删改查*/
//返回 实体对象   vo-实体bean
dalClient.queryForObject("description.queryDescriptionById", vo, CpDescription.class);
 
//执行sql无返回   vo-实体bean
dalClient.execute("description.updateDescriptionById", vo);
  
//bootstrap 分页返回
PaginationResult<List<CpDescription>> result = dalClient.queryForList("description.queryDescriptionListPage", vo,CpDescription.class, pagination);
   
//保存实体对象 vo-实体bean
dalClient.persist(vo);
   
//事物处理（回滚-未测试）
public String deleteNewsInfoById(final String ids) {
        dalClient.getTransactionTemplate().execute(new CallBackTemplate<Integer>() {
            @Override
            public Integer invoke() {
              /**
                                             相关代码
              
              */
               return null;
           }
        });
        return null;
}
 
//--------------------------相关sqlMap处理
<sql id="queryFixCodeByCodeType">
    <![CDATA[
      select * from t_fix_code where 1=1
      <#if codeType?exists && codeType!=""> AND code_type= :codeType </#if>
    ]]>
</sql>
 


   