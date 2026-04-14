from mlxtend.frequent_patterns import apriori
from mlxtend.preprocessing import TransactionEncoder
import pandas as pd

data=[['eggs','milk','bread'],['eggs','apple'],
      ['milk','bread'],['apple','milk'],
      ['milk','apple','bread']]

te=TransactionEncoder()
df=pd.DataFrame(te.fit(data).transform(data),columns=te.columns_)

print(apriori(df,min_support=0.4,use_colnames=True))
