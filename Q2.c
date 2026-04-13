#include <stdio.h>
#include <stdlib.h>

int main() {
    int req[] = {176,79,34,60,92,11,41,114};
    int n = 8;
    int head = 65;
    int total = 0;

    // Sort requests
    for(int i=0;i<n-1;i++) {
        for(int j=0;j<n-i-1;j++) {
            if(req[j] > req[j+1]) {
                int temp = req[j];
                req[j] = req[j+1];
                req[j+1] = temp;
            }
        }
    }

    printf("Order of service:\n");

    // Move LEFT
    for(int i=n-1;i>=0;i--) {
        if(req[i] < head) {
            printf("%d -> ", req[i]);
            total += abs(head - req[i]);
            head = req[i];
        }
    }

    // Go to 0 (end)
    total += head;
    head = 0;

    // Move RIGHT
    for(int i=0;i<n;i++) {
        if(req[i] > head) {
            printf("%d -> ", req[i]);
            total += abs(head - req[i]);
            head = req[i];
        }
    }

    printf("\nTotal Head Movement = %d\n", total);
    return 0;
}
