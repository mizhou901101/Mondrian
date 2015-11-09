package org.demo.core.dao.impl;

import java.util.List;

import org.demo.common.jodo.UserDO;
import org.demo.core.dao.UserDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;


public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	public boolean insertUser(UserDO userDO) {
		
		return false;
	}

	public boolean deleteUser(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateUser(UserDO userDO) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("rawtypes")
	public UserDO queryUser(Integer id) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("user.selectUserById");
		query.setInteger("id", id);
		List result = query.list();
		if (!CollectionUtils.isEmpty(result)) {
			return (UserDO)result.get(0);
		}
		return null;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
//	public static void main(String args[]) {
//		int A[] = {0, 1, 3, -2, 0, 1, 0, -3, 2, 3};
//		System.out.println(solution(A));
//	}
//	
//	public static int solution(int[] A) {
//        // write your code in Java SE 8
//        boolean isStart = false;
//        int l = A.length;
//        int P = -1;
//        int Q = -1;
//        int R = -1;
//        int depth = -1;
//        for (int i = 0; i < l; i ++) {
//            if (!isStart && i + 1 >= l - 1) {
//                return -1;
//            } else if (!isStart && A[i] <= A[i + 1]) {
//                continue;
//            } else if (!isStart && A[i] > A[i + 1]) {
//            	isStart = true;
//            }
//            
//            if (isStart && i + 1 == l - 1) {
//            	if (A[i] < A[i + 1]) {
//            		R = i + 1;
//            		if (P != -1 && Q != -1 && R != -1) {
//            			int d = min(A, P, Q, R);
//            			if (d > depth) {
//            				depth = d;
//            			}
//            		}
//            	}
//                break;
//            }
//            
//            if (isStart && A[i] > A[i + 1]) {
//                if (P == -1) {
//                    P = i;
//                } else if (Q != -1) {
//                    R = i;
//                }
//            } else if (isStart && A[i] < A[i + 1]) {
//            	if (Q == -1) {
//            		Q = i;
//            	}
//            }
//            
//            if (P != -1 && Q != -1 && R != -1) {
//                int d = min(A, P, Q, R);
//                if (d > depth) {
//                    depth = d;
//                }
//                P = R;
//                Q = -1;
//                R = -1;
//            }
//        }
//        return depth;
//    }
//    
//    public static int min(int A[], int P, int Q, int R) {
//        if (A[P] - A[Q] >= A[R] - A[Q]) {
//            return A[R] - A[Q];
//        } else {
//            return A[P] - A[Q];
//        }
//    }

}
