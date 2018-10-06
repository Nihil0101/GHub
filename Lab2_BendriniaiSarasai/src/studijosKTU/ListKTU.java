/**
 * @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2014 09 23
 *
 * Tai pirmoji kompleksinės duomenų struktūros klasė, kuri leidžia apjungti
 * atskirus objektus į vieną visumą - sąrašą. Objektai, kurie bus dedami į
 * sąrašą, turi tenkinti interfeisą Comparable<E>.
 *
 * Užduotis: Peržiūrėkite ir išsiaiškinkite pateiktus metodus. Metodų algoritmai
 * yra aptarti paslaitos metu. Realizuokite nurodytus metodus.
 *****************************************************************************
 */
package studijosKTU;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Koreguota 2015-09-18
 * @author Aleksis
 * @param <E> Sąrašo elementų tipas (klasė)
 */
public class ListKTU<E extends Comparable<E>>
		implements ListADT<E>, Iterable<E>, Cloneable {

	private Node<E> first;   // rodyklė į pirmą mazgą
	private Node<E> last;    // rodyklė į paskutinį mazgą
	private Node<E> current; // rodyklė į einamąjį mazgą, naudojama getNext
	private int size;        // sąrašo dydis, tuo pačiu elementų skaičius
        transient Object[] elementData;
//        private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
//        private static final int DEFAULT_CAPACITY = 10;

	/**
	 * Constructs an empty list.
	 */
	public ListKTU() {
	}

	/**
	 * metodas add įdeda elementą į sąrašo pabaigą
	 * @param e - tai įdedamas elementas (jis negali būti null)
	 * @return true, jei operacija atlikta sėkmingai
	 */
	@Override
	public boolean add(E e) {
		if (e == null) {
			return false;   // nuliniai objektai nepriimami
		}
		if (first == null) {
			first = new Node<>(e, first);
			last = first;
		} else {
			Node<E> e1 = new Node(e, null);
			last.next = e1;
			last = e1;
		}
		size++;
		return true;
	}

	/**
	 * Įterpia elementą prieš k-ąją poziciją
	 *
	 * @param k pozicija
	 * @param e įterpiamasis elementas
	 * @return jei k yra blogas, grąžina null
	 */
	@Override
	public boolean add(int k, E e) {
             if (e == null) {
		return false;
            }
            if (k < 0 || k > size) {
               return false;
            }
            
            if (first == null || k == 0) { // pirmas į sąrašą
                first = new Node<E>(e, first);
                last = first;
            } else {  // prijungiame į pabaigą
                current = first.findNode(k-1);   
                Node<E> e1 = new Node(e, null);
                current.next = e1;
                current = e1;
            }
            size++; // ListKTU klasės laukas
            return true;
        }

//	public boolean addALL(int index, ListKTU<? extends E> c){
//            
//        }
        
        
        public boolean addAll(int index, ListKTU<? extends E> list) {
        if (index > size || index < 0)
            return false;
       
        int numMoved = size - index;
        int i = 0;
        if (numMoved > 0)
            for (;;) {
                for(Node<E> e = first; e != null; e = e.next){
                    if(i == index){
                        ListKTU<E> temporaryList = new ListKTU<E>();
                        for(Node<E> el = e.next; el != null; el = el.next){
                            temporaryList.add(el.element);
                        }
                        for (E ele: list) {
                            Node<E> el = new Node(ele, null);
                            e.next = el;
                            e = e.next;
                             size++;
                        }
                        for (E ele: temporaryList) {
                            Node<E> el = new Node(ele, null);
                            e.next = el;
                            e = e.next;
                        }
                        return true;
                    }
                    i++;
                }
            }
            return true;
        }
        
//         public boolean addAll(int index, ListKTU<? extends E> c) {
//        if (index > size || index < 0)
//            return false;
//        
//        Object[] a = c.toArray();
//        int numNew = a.length;
//        //ensureCapacityInternal(size + numNew);  // Increments modCount
//
//        int numMoved = size - index;
//        if (numMoved > 0)
//            System.arraycopy(elementData, index, elementData, index + numNew,
//                             numMoved);
//
//        System.arraycopy(a, 0, elementData, index, numNew);
//        size += numNew;
//        return numNew != 0;
//    }
        public boolean remove(Object o) {
                if (o == null) {
                    for (ListKTU.Node<E> x = first; x != null; x = x.next) {
                        if (x.element == null) {
                        remove(x);
                            return true;
                        }
                 }
                } else {
                       int i= 0;
                       for (ListKTU.Node<E> x = first; x != null; x = x.next) {
                        if (o.equals(x.element)) {
                            remove(i);
                            return true;
                        }
                        i++;
                    }
                }
                return false;
    }
        
	/**
	 *
	 * @return sąrašo dydis (elementų kiekis)
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * patikrina ar sąrašas yra tuščias
	 *
	 * @return true, jei tuščias
	 */
	@Override
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Išvalo sąrašą
	 */
	@Override
	public void clear() {
		size = 0;
		first = null;
		last = null;
		current = null;
	}

	/**
	 * Grąžina elementą pagal jo indeksą
	 *		(pradinis indeksas 0)
	 * @param k indeksas
	 * @return k-ojo elemento reikšmę; jei k yra blogas, gąžina null
	 */
	@Override
	public E get(int k) {
		if (k < 0 || k >= size) {
			return null;
		}
		current = first.findNode(k);
		return current.element;
	}

	/**
	 * Pakeičia k-ojo elemento reikšmę
	 *
	 * @param k keičiamo elemento indeksas
	 * @param e nauja elemento reikšmė
	 * @return senoji reikšmė
	 */
	//@Override
	public E set(int k, E el) {
//            int i = 0;
//             for (;;) {
//		for (Node<E> e = first; e != null; e = e.next) {
//                        if(i == k){
//                            current = null;
//                            Node<E> ele = new Node(e, null);
//                            current = ele;
//                            return  current.GetData();
//                        }
//                    i++;
//                }
        current = first.findNode(k);   
        E oldElement = current.element;
        current.element = el;
        return oldElement;     
            
	}

	/**
	 * pereina prie kitos reikšmės ir ją grąžina
	 *
	 * @return kita reikšmė
	 */
	@Override
	public E getNext() {
		if (current == null) {
			return null;
		}
		current = current.next;
		if (current == null) {
			return null;
		}
		return current.element;
	}

	/**
	 * Šalina elementą pagal indeksą
	 *
	 * @param k šalinamojo indeksas
	 * @return pašalintas elementas
	 */
	@Override
	public E remove(int k) {
            if (k<0 || k>=size) return null;  // jei k yra blogas, grąžina null
	    Node<E> actual = null;
            if (k == 0) {   // šaliname elementą, esantį pradžioje
            actual=first; 
                first = actual.next;
                if (first == null) 
                 last=null;
           } 
           else {     // šaliname elementą, esantį tolimesnėje sekoje
                Node<E> previous = first.findNode(k-1);
                actual = previous.next;
                previous.next = actual.next;
                if (actual.next == null) last = previous;
            }
            size--;
            return actual.element;
        }

        

	/**
	 *
	 * @return sąrašo kopiją
	 */
	@Override
	public ListKTU<E> clone() {
		ListKTU<E> cl = null;
		try {
			cl = (ListKTU<E>) super.clone();
		} catch (CloneNotSupportedException e) {
			Ks.ern("Blogai veikia ListKTU klasės protėvio metodas clone()");
			System.exit(1);
		}
		if (first == null) {
			return cl;
		}
		cl.first = new Node<>(this.first.element, null);
		Node<E> e2 = cl.first;
		for (Node<E> e1 = first.next; e1 != null; e1 = e1.next) {
			e2.next = new Node<>(e2.element, null);
			e2 = e2.next;
			e2.element = e1.element;
		}
		cl.last = e2.next;
		cl.size = this.size;
		return cl;
	}
    //  !

	/**
	 * Formuojamas Object masyvas (E tipo masyvo negalima sukurti) ir surašomi
	 * sąrašo elementai
	 *
	 * @return sąrašo elementų masyvas
	 */
	public Object[] toArray() {
		Object[] a = new Object[size];
		int i = 0;
		for (Node<E> e1 = first; e1 != null; e1 = e1.next) {
			a[i++] = e1.element;
		}
		return a;
	}

	/**
	 * Masyvo rikiavimas Arrays klasės metodu sort
	 */
	public void sortSystem() {
		Object[] a = this.toArray();
		Arrays.sort(a);
		int i = 0;
		for (Node<E> e1 = first; e1 != null; e1 = e1.next) {
			e1.element = (E) a[i++];
		}
	}

	/**
	 * Rikiavimas Arrays klasės metodu sort pagal komparatorių
	 *
	 * @param c komparatorius
	 */
	public void sortSystem(Comparator<? super E> c) {
		Object[] a = this.toArray();
		Arrays.sort(a, (Comparator) c);
		int i = 0;
		for (Node<E> e1 = first; e1 != null; e1 = e1.next) {
			e1.element = (E) a[i++];
		}
	}

	/**
	 * Sąrašo rikiavimas burbuliuko metodu
	 */
	public void sortBuble() {
		if (first == null) {
			return;
		}
		for (;;) {
			boolean jauGerai = true;
			Node<E> e1 = first;
			for (Node<E> e2 = first.next; e2 != null; e2 = e2.next) {
				if (e1.element.compareTo(e2.element) > 0) {
					E t = e1.element;
					e1.element = e2.element;
					e2.element = t;
					jauGerai = false;
				}
				e1 = e2;
			}
			if (jauGerai) {
				return;
			}
		}
	}

	/**
	 * Sąrašo rikiavimas burbuliuko metodu pagal komparatorių
	 *
	 * @param c komparatorius
	 */
	public void sortBuble(Comparator<? super E> c) {
		if (first == null) {
			return;
		}
		for (;;) {
			boolean jauGerai = true;
			Node<E> e1 = first;
			for (Node<E> e2 = first.next; e2 != null; e2 = e2.next) {
				if (c.compare(e1.element, e2.element) > 0) {
					E t = e1.element;
					e1.element = e2.element;
					e2.element = t;
					jauGerai = false;
				}
				e1 = e2;
			}
			if (jauGerai) {
				return;
			}
		}
	}

	/**
	 * Sukuria iteratoriaus objektą sąrašo elementų peržiūrai
	 *
	 * @return iteratoriaus objektą
	 */
	@Override
	public Iterator<E> iterator() {
		return new ListIteratorKTU();
	}

    

	/**
	 * Iteratoriaus metodų klasė
	 */
	class ListIteratorKTU implements Iterator<E> {

		private Node<E> iterPosition;

		ListIteratorKTU() {
			iterPosition = first;
		}

		@Override
		public boolean hasNext() {
			return iterPosition != null;
		}

		@Override
		public E next() {
			E d = iterPosition.element;
			iterPosition = iterPosition.next;
			return d;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Studentams reikia realizuoti ListItr.remove()");
		}
	}

	/**
	 * Vidinė mazgo klasė
	 *
	 * @param <E> mazgo duomenų tipas
	 */
	private static class Node<E> {

		private E element;    // ji nematoma už klasės ListKTU ribų
		private Node<E> next; // next - kaip įprasta - nuoroda į kitą mazgą

		Node(E data, Node<E> next) { //mazgo konstruktorius
			this.element = data;
			this.next = next;
		}

                public E GetData()
                {
                    return this.element;
                }
		/**
		 * Suranda sąraše k-ąjį mazgą
		 *
		 * @param k ieškomo mazgo indeksas (prasideda nuo 0)
		 * @return surastas mazgas
		 */
		public Node<E> findNode(int k) {
			Node<E> e = this;
			for (int i = 0; i < k; i++) {
				e = e.next;
			}
			return e;
		}
	} // klasės Node pabaiga
        
        public boolean containsAll(ListKTU<E> list) {
           if (first == null) {
			return false;
		}
		for (;;) {
			for (E ele: list) {
                            boolean been = false;
                                for(Node<E> e = first; e != null; e = e.next){
                                    if(e.element.compareTo(ele) == 0){
                                        been = true;
                                        //break;
                                    }
                                }
                                if(!been)return false;
                        }
                return true;
		}
        }
        
        
        public boolean contains(E el) {
            for (;;) {
                    for(Node<E> e = first; e != null; e = e.next){
                        if(e.element.compareTo(el) == 0){
                            return true;
                        }
                    }
                    return false;
            }           
        }
    /**
     *
     * @param e
     * @return
     */
    public int lastIndexOf(E el){
            if (first == null) {
			return -1;
		}
            int index = -1;
            int i = 0;
                
            for (;;) {
		for (Node<E> e = first; e != null; e = e.next) {
                        if(e.equals(el)){
                            index = i;
                        }
                    i++;
                }
            return index;
            }
        }
    
        public boolean addFirst(E e) {
            if (e == null) {
                return false;
            }
             if (first == null) {
		first = new Node<>(e, first);
		last = first;
             }
             else{
		Node<E> el = new Node(e, null);
                first.next = el;
             }
             size++;
             return true;
    }
    
}