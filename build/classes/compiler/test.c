int a;
int b;

int func(int c, int d) {
	int e;
	int f;
	e = 123 + c;
	return e;
}

int main(void) {
	int temp;
	temp = func(1, 2);
	
	while (temp < 5) {
		temp = temp + 1;
	}
	
	if (temp == 1) {
		if (temp == 2) {
			temp = 11;
		} else {
			temp = 5;
		}
	} else {
		if (temp == 3) {
			temp = 12;
		} else {
			temp = 4;
		}
		if (temp == 100) {
			temp = 10;
		} else {
			temp = 9;
		}
	}
	
	temp = 44;
}
