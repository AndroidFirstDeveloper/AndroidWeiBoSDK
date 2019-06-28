/*     */ package com.sina.sso;
/*     */ import android.os.Binder;
import android.os.IBinder;
/*     */ import android.os.IInterface;
/*     */ import android.os.Parcel;
/*     */ import android.os.RemoteException;
/*     */ 
/*     */ public interface RemoteSSO extends IInterface {
/*     */   String getPackageName() throws RemoteException;
/*     */   
/*     */   String getActivityName() throws RemoteException;
/*     */   
/*     */   String getLoginUserName() throws RemoteException;
/*     */   
/*     */   public static abstract class Stub extends Binder implements RemoteSSO {
/*  15 */     public Stub() { attachInterface(this, "com.sina.sso.RemoteSSO"); }
/*     */     
/*     */     private static final String DESCRIPTOR = "com.sina.sso.RemoteSSO";
/*     */     static final int TRANSACTION_getPackageName = 1;
/*     */     static final int TRANSACTION_getActivityName = 2;
/*     */     static final int TRANSACTION_getLoginUserName = 3;
/*     */     
/*     */     public static RemoteSSO asInterface(IBinder obj) {
/*  23 */       if (obj == null) {
/*  24 */         return null;
/*     */       }
/*  26 */       IInterface iin = obj.queryLocalInterface("com.sina.sso.RemoteSSO");
/*  27 */       if (iin != null && iin instanceof RemoteSSO) {
/*  28 */         return (RemoteSSO)iin;
/*     */       }
/*  30 */       return new Proxy(obj);
/*     */     }
/*     */ 
/*     */     
/*  34 */     public IBinder asBinder() { return this; } public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
/*     */
/*     */
/*     */       String _result;
/*  38 */       switch (code) {
/*     */ 
/*     */         
/*     */         case 1598968902:
/*  42 */           reply.writeString("com.sina.sso.RemoteSSO");
/*  43 */           return true;
/*     */ 
/*     */         
/*     */         case 1:
/*  47 */           data.enforceInterface("com.sina.sso.RemoteSSO");
/*  48 */           _result = getPackageName();
/*  49 */           reply.writeNoException();
/*  50 */           reply.writeString(_result);
/*  51 */           return true;
/*     */ 
/*     */         
/*     */         case 2:
/*  55 */           data.enforceInterface("com.sina.sso.RemoteSSO");
/*  56 */           _result = getActivityName();
/*  57 */           reply.writeNoException();
/*  58 */           reply.writeString(_result);
/*  59 */           return true;
/*     */ 
/*     */         
/*     */         case 3:
/*  63 */           data.enforceInterface("com.sina.sso.RemoteSSO");
/*  64 */           _result = getLoginUserName();
/*  65 */           reply.writeNoException();
/*  66 */           reply.writeString(_result);
/*  67 */           return true;
/*     */       } 
/*     */       
/*  70 */       return super.onTransact(code, data, reply, flags);
/*     */     }
/*     */     
/*     */     private static class Proxy
/*     */       implements RemoteSSO {
/*     */       private IBinder mRemote;
/*     */       
/*  77 */       Proxy(IBinder remote) { this.mRemote = remote; }
/*     */ 
/*     */ 
/*     */       
/*  81 */       public IBinder asBinder() { return this.mRemote; }
/*     */ 
/*     */ 
/*     */       
/*  85 */       public String getInterfaceDescriptor() throws RemoteException { return "com.sina.sso.RemoteSSO"; }
/*     */       
/*     */       public String getPackageName() throws RemoteException {
/*     */         String _result;
/*  89 */         Parcel _data = Parcel.obtain();
/*  90 */         Parcel _reply = Parcel.obtain();
/*     */         
/*     */         try {
/*  93 */           _data.writeInterfaceToken("com.sina.sso.RemoteSSO");
/*  94 */           this.mRemote.transact(1, _data, _reply, 0);
/*  95 */           _reply.readException();
/*  96 */           _result = _reply.readString();
/*     */         } finally {
/*     */           
/*  99 */           _reply.recycle();
/* 100 */           _data.recycle();
/*     */         } 
/* 102 */         return _result;
/*     */       }
/*     */       public String getActivityName() throws RemoteException {
/*     */         String _result;
/* 106 */         Parcel _data = Parcel.obtain();
/* 107 */         Parcel _reply = Parcel.obtain();
/*     */         
/*     */         try {
/* 110 */           _data.writeInterfaceToken("com.sina.sso.RemoteSSO");
/* 111 */           this.mRemote.transact(2, _data, _reply, 0);
/* 112 */           _reply.readException();
/* 113 */           _result = _reply.readString();
/*     */         } finally {
/*     */           
/* 116 */           _reply.recycle();
/* 117 */           _data.recycle();
/*     */         } 
/* 119 */         return _result;
/*     */       }
/*     */       public String getLoginUserName() throws RemoteException {
/*     */         String _result;
/* 123 */         Parcel _data = Parcel.obtain();
/* 124 */         Parcel _reply = Parcel.obtain();
/*     */         
/*     */         try {
/* 127 */           _data.writeInterfaceToken("com.sina.sso.RemoteSSO");
/* 128 */           this.mRemote.transact(3, _data, _reply, 0);
/* 129 */           _reply.readException();
/* 130 */           _result = _reply.readString();
/*     */         } finally {
/*     */           
/* 133 */           _reply.recycle();
/* 134 */           _data.recycle();
/*     */         } 
/* 136 */         return _result;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\weibosdkcore.jar!\com\sina\sso\RemoteSSO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.5
 */