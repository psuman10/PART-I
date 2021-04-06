package Week2A;

public class Stack {
int size;
int top = -1;
char stk[];

Stack(int size) {
this.size = size;
stk = new char[size];
}

void push(char data) {
if(!isFull()) {
stk[++top] = data;
}

}

char pop() {
if(!isEmpty()) {
return stk[top--];
} else {
return 'a';
}

}

boolean isFull() {
return top==size-1;
}

boolean isEmpty() {
return top==-1;
}

int peak() {
return top;
}

}