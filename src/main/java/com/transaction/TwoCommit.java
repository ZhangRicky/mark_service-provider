package com.transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

/**
 * 事务两段提交
 * @author Mark
 *
 */
public class TwoCommit {
	XADataSource xads;
	XAConnection xacn1;XAConnection xacn2;
	XAResource xaRes1;XAResource xaRes2;
	
	Xid xid1;
	Xid xid2;
	Connection conn1;Connection conn2;	//创建数据库连接
	Statement stment1;Statement stment2;	//创建statement
	
	int ret;
	
	public void test() throws SQLException{
		try {
			xads = (XADataSource) getDataSource();
			//连接1
			xacn1 =xads.getXAConnection("username1", "password1");
			xaRes1 =xacn1.getXAResource();
			conn1 = xacn1.getConnection();
			stment1 =conn1.createStatement();
			xid1 = new MyXid(100, new byte[]{0x01}, new byte[]{0x02});
			
			xaRes1.start(xid1, XAResource.TMNOFLAGS);						//开始
			stment1.executeUpdate("insert into test_table values(100)");	//执行SQL
			xaRes1.end(xid1, XAResource.TMSUCCESS);						//结束
			//连接2
			xacn2 =xads.getXAConnection("username2", "password2");
			xaRes2 =xacn2.getXAResource();
			conn2 = xacn2.getConnection();
			stment2 =conn2.createStatement();
					
			//查看是否使用的是同一个资源管理程序，如果是就是用xid1的资源管理
			if(xaRes2.isSameRM(xaRes1)){
				xaRes2.start(xid1, XAResource.TMNOFLAGS);						//开始
				stment2.executeUpdate("insert into test_table values(100)");	//执行SQL
				xaRes2.end(xid1, XAResource.TMSUCCESS);						//结束
			}else{
				xid2 = new MyXid(100, new byte[]{0x01}, new byte[]{0x02});
				xaRes2.start(xid2, XAResource.TMNOFLAGS);						//开始
				stment2.executeUpdate("insert into test_table values(100)");	//执行SQL
				xaRes2.end(xid2, XAResource.TMSUCCESS);	
				ret = xaRes2.prepare(xid2);
				if(ret == XAResource.XA_OK){
					xaRes2.commit(xid2, false);
				}
			}
			ret = xaRes1.prepare(xid1);
			if(ret == XAResource.XA_OK){
				xaRes1.commit(xid1, false);
			}
			//stment.executeUpdate("insert into test_table values(200)");	//该更新在事务范围之外，所以不受事务控制
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
//			stment.close();
//			conn.close();
//			xacn.close();
		}
	}	
			
			
			
	public static DataSource getDataSource(){
		return null;
	}
	
	public void returnException() throws XAException{
		Xid[] xids;
		xids = xaRes1.recover(XAResource.TMENDRSCAN | XAResource.TMSTARTRSCAN);
		for(int i=0;xids != null ;){
			try {
				xaRes1.rollback(xids[i]);	//尝试回退事务
			} catch (XAException e) {
				try {
					xaRes1.forget(xids[i]);	//回退失败就忘记事务
				} catch (Exception e2) {			
				}
				System.out.println("rollback/forget"+e.errorCode);
			}
		}
	}
}

