/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.util.Iterator;
import java.util.ListIterator;


/**
 *
 * @author dilan
 * @param <E>
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E> {
    
    private Node<E> first;
    private Node<E> last;
    private int current;

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>(){
            
            Node<E> i =first;
            @Override
            public boolean hasNext() {
                return i!=null;
            }

            @Override
            public E next() {
                E temp = i.data;
                i=i.next;
                return temp;
            }
        
        };
        return it;
    }
    private Node<E> nodeIndex(int index){
        if( this.isEmpty()||index <0 || index>current) return null;
        Node<E> node= first;
        for (int i=0; i<index; i++) node=node.next;
        return node;
    }
    
    public ListIterator<E> listIterator(){
        ListIterator<E> lit = new ListIterator<E>(){
            Node<E> low =first;
            Node<E> high =last;
            @Override
            public boolean hasNext() {
                return low!=null;
            }

            @Override
            public E next() {
                E tmp = low.data;
                low = low.next;
                return tmp;
            }

            @Override
            public boolean hasPrevious() {
                return high!=null;
            }

            @Override
            public E previous() {
                E tmp = high.data;
                high = high.previous;
                return tmp;
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void set(E arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void add(E arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        
        
        };
        return lit;
    }
    
    
    public ListIterator<E> listIterator(int index){
        ListIterator<E> lit = new ListIterator<E>(){
            
            Node <E> i = nodeIndex(index);
            int c= index;
            @Override
            public boolean hasNext() {
                return i!=null;
            }

            @Override
            public E next() {
                E tmp = i.data;
                i = i.next;
                c++;
                return tmp;
            }

            @Override
            public boolean hasPrevious() {
                return i!=null;
            }

            @Override
            public E previous() {
                E tmp = i.data;
                i = i.previous;
                c--;
                return tmp;
            }

            @Override
            public int nextIndex() {
                return c+1;
            }

            @Override
            public int previousIndex() {
                return c-1;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void set(E arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void add(E arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        
        
        };
        return lit;
    }
    
    // DEFINIENDO CLASE NODE
     private class Node<E>{
        private E data;
        private Node<E> next;
        private Node<E> previous;
        public Node(E data){
            this.data =data;
            
        }
    }
    //***********************
     
     
    @Override
    public boolean addFirst(E element) {
        if(element==null) return false;
        Node<E> n = new Node<>(element);
        if (this.isEmpty())first = last = n;
        else{
            n.next=first;
            first.previous=n;
            first=n;
        }
        current++;
        return true;
    }

    @Override
    public boolean addLast(E element) {
        if(element==null) return false;
        Node<E> n = new Node<>(element);
        if (this.isEmpty())first = last = n;
        else{
            n.previous=last;
            last.next=n;
            last=n;
        }
        current++;
        return true;

    }

    @Override
    public boolean addIndex(E element, int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E get(int index) {
        Node<E> nodo =first;
        if(index < 0) return null;
        for (int i = 0; i<index;i++) nodo= nodo.next;
        return nodo.data;
            
    }

    @Override
    public int size() {
        return current;
    }

    @Override
    public boolean contains(E element) {
        Node<E> i =first;
        if(element==null) return false;
        while(i!=null){
            if(i.data.equals(element)) return true;
            i=i.next;
        }
        return false;
    }

    @Override
    public boolean removeFirst() {
        if(this.isEmpty()) return false;
        if(first == last){
            first.data=null;
            first = last= null;
        }
        else{
            first.next.previous=null;
            first.data=null;
            first=first.next;  
        }
        current--;
        return true; 
    }

    @Override
    public boolean removeLast() {
        if(this.isEmpty()) return false;
        if(first == last){
            first.data=null;
            first = last= null;
        }
        else{
            last.previous.next=null;
            last.data=null;
            last=last.previous;  
        }
        current--;
        return true; 
    }

    @Override
    public boolean removeIndex(int index) {
        if(this.isEmpty()) return false;
        Node<E> nodo = first;
        if (index==0){
            this.removeFirst();
            return true;
        }
        if(index==current-1){
            this.removeLast();
            return true;
        }
        for(int i =0; i<index; i++) nodo=nodo.next;
        nodo.previous.next = nodo.next;
        nodo.next.previous=nodo.previous;
        current--;
        return true;
    }
    
 
    @Override
    public E getFrist() {
        return first.data;
    }

    @Override
    public E getLast() {
        return last.data;
    }
    
    @Override
    public boolean isEmpty() {
        return first==null && last==null;
    }
    
    public void reverse(){
        reverse(first,last);
    }
    
    private void reverse(Node<E> low, Node<E> high){
        if(!(low.previous==high || low==high)){
            E temp=low.data;
            low.data=high.data;
            high.data=temp;
            reverse(low.next, high.previous);
        }
    }
    
    
    
    //*****************Ejercicio 5******************
    // Iterativo 
    private boolean removeNode(Node<E> nodo){
        if(nodo==null) return false;
        if(nodo.next== null) return this.removeLast();
        if(nodo.previous==null) return this.removeFirst();
        else{
            nodo.next.previous=nodo.previous;
            nodo.previous.next= nodo.next;
            nodo.data=null;
            current--;
            return true;
        } 
    }
  
   
    //*****************Ejercicio 7 y 9******************
    public Node<E> getNode(int index){
        Node<E> nodo = first;
        for(int i=0; i< index; i++) nodo = nodo.next;
        return nodo;
    }
    
    public boolean removeNNodes(Node<E> nodo , int pasos){
        Node<E> tmp= nodo;
        int contador=0;
        if(pasos<0) return false;
        while(pasos!=contador){
            if(!removeNode(tmp.next)) return true;
            contador++;
        }
        return true;
    }
    
   
    
    @Override
    public String toString(){
        Node<E> nodo=first;
        StringBuilder data = new StringBuilder();
        data.append("[");
        for(int i=0; i<current-1;i++){
            data.append(nodo.data);
            data.append(",");
            nodo= nodo.next;
        }
        data.append(last.data);
        data.append("]");
        return data.toString();
    }
    
    @Override
    public boolean equals(Object o){
        if (o.getClass()!= this.getClass() || o==null) return false;
        if(o==this)return true;
        DoublyLinkedList<E> l= (DoublyLinkedList<E>) o;
        if (this.current!=l.current) return false;
        else{
            Node<E> nodo=first;
            Node<E> nodoL=l.first;
            for(int i=0; i<current-1;i++){
                if(nodo.data!=nodoL.data) return false;
                nodo= nodo.next;
                nodoL= nodoL.next;   
            }
        }
        return true;
    }
    
}
