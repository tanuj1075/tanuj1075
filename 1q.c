#include <stdio.h>

int main() {
    int n = 5, m = 3;

    int alloc[5][3] = {
        {0,1,0},
        {2,0,0},
        {3,0,3},
        {2,1,1},
        {0,0,2}
    };

    int max[5][3] = {
        {0,0,0},
        {2,0,2},
        {0,0,0},
        {1,0,0},
        {0,0,2}
    };

    int avail[3] = {0,0,0};

    int need[5][3];
    int finish[5] = {0};
    int safeSeq[5];
    int count = 0;

    // Calculate Need = Max - Allocation
    printf("\nNeed Matrix:\n");
    for(int i=0;i<n;i++) {
        for(int j=0;j<m;j++) {
            need[i][j] = max[i][j] - alloc[i][j];
            printf("%d ", need[i][j]);
        }
        printf("\n");
    }

    // Banker's Algorithm
    while(count < n) {
        int found = 0;

        for(int i=0;i<n;i++) {
            if(!finish[i]) {
                int j;
                for(j=0;j<m;j++) {
                    if(need[i][j] > avail[j])
                        break;
                }

                if(j == m) {
                    for(int k=0;k<m;k++)
                        avail[k] += alloc[i][k];

                    safeSeq[count++] = i;
                    finish[i] = 1;
                    found = 1;
                }
            }
        }

        if(!found) {
            printf("\nSystem is NOT in safe state!\n");
            return 0;
        }
    }

    printf("\nSystem is in SAFE state.\nSafe sequence: ");
    for(int i=0;i<n;i++)
        printf("P%d ", safeSeq[i]);

    printf("\n");
    return 0;
}
