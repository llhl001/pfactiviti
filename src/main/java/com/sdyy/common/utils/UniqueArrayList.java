/*    */ package com.sdyy.common.utils;

/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ public class UniqueArrayList<E> extends ArrayList<E>
/*    */   implements Cloneable
/*    */ {
/*    */   private static final long serialVersionUID = 7739843329266051669L;
/*    */ 
/*    */   public void add(int index, E element)
/*    */   {
/* 36 */     if (!super.contains(element)) super.add(index, element);
/*    */   }
/*    */ 
/*    */   public boolean add(E element)
/*    */   {
/* 41 */     if (!super.contains(element)) return super.add(element);
/* 42 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean addAll(Collection<? extends E> collection)
/*    */   {
/* 47 */     for (Iterator i$ = collection.iterator(); i$.hasNext(); ) { E e = (E)i$.next();
/*    */ 
/* 49 */       add(e);
/*    */     }
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean addAll(int index, Collection<? extends E> collection)
/*    */   {
/* 56 */     List cTmp = new ArrayList();
/* 57 */     for (Iterator i$ = collection.iterator(); i$.hasNext(); ) { Object e = i$.next();
/*    */ 
/* 59 */       if (!super.contains(e))
/*    */       {
/* 61 */         cTmp.add(e);
/*    */       }
/*    */     }
/* 64 */     return super.addAll(index, cTmp);
/*    */   }
/*    */ 
/*    */   public UniqueArrayList<E> clone()
/*    */   {
/* 69 */     return (UniqueArrayList)super.clone();
/*    */   }
/*    */ }

/* Location:           H:\自学资料\wabacus4.0-release\wabacus-4.0.jar
 * Qualified Name:     com.wabacus.util.UniqueArrayList
 * JD-Core Version:    0.6.2
 */