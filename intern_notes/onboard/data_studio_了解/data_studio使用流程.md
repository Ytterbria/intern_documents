data studio 大致框架流程

RAM 负责一切的权限管控，表，数据库权限，计算资源等，都通过与RAM的通信来检查相关的权限。 

Scheduler 数据作业的调度系统，是与各个引擎之间交互的唯一入口，计算任务都由scheduler提交到计算引擎
计算引擎包括presto，spark，clickhouse等，将HDFS，S3，CSV等数据进行双向转换


*开发和使用流程
平台环境划分
DEV  用于调试任务，快速迭代，定时任务发布到该环境不需要审批流程。
Staging 环境配置与prod环境类似，用于正式发布之前的测试和验证，体验服
Prod 严格的实际生产使用环境

*marker + offset
input marker : 标记即将要使用的数据源是否准备好，或者上游任务是否执行成功。 
可以auto parse，也可以自定义。自定义需要配置对应的offset（时间粒度大于week） ，和markers，确定具体需要的实例和哪几个

output marker:  标记任务是否已经成功输出数据，或者任务是否运行成功
上游任务的output marker可能就是下一个任务的input marker，不同的任务之间便可以通过marker来进行联系
可以通过auto parse按钮自动解析任务脚本解析
还可以自定义output marker，包含output marker name，offset字段

Offset可以根据具体的时间需求来设定， -2 month之类，还可以通过markers设定 更加细化的时间粒度

***数据开发流程
1.创建目标表（包含需要接收的上游数据表的相关数据）
2.选一个合适的目录创建任务类型（presto，spark，clickhouse等）
3.在任务之重可以进行相应的sql，逻辑便携，可以运行，并检查相关日志等。 如果任务没有问题，获得output文件，保存并发送出邮件，邮件中包含相应附件，信息等
4.配置相应的任务运行周期，设置相应的scheduler，重试机制，alarm机制等
5.发布任务 和运维任务， 选择相应的环境，后续可以查看相应任务的运行结果等





 