{
 "cells": [
  {
   "cell_type": "code",
   "execution_count": 1,
   "id": "e1e29f1b-a786-4135-a70b-e50a5e95d976",
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "Prediction for Weather=Overcast and Temperature=Mild: Yes\n",
      "\n",
      "Confusion Matrix:\n",
      " [[1 0]\n",
      " [0 2]]\n",
      "\n",
      "Classification Report:\n",
      "               precision    recall  f1-score   support\n",
      "\n",
      "          No       1.00      1.00      1.00         1\n",
      "         Yes       1.00      1.00      1.00         2\n",
      "\n",
      "    accuracy                           1.00         3\n",
      "   macro avg       1.00      1.00      1.00         3\n",
      "weighted avg       1.00      1.00      1.00         3\n",
      "\n"
     ]
    }
   ],
   "source": [
    "import pandas as pd\n",
    "from sklearn import preprocessing\n",
    "from sklearn.model_selection import train_test_split\n",
    "from sklearn.preprocessing import LabelEncoder\n",
    "from sklearn.neighbors import KNeighborsClassifier\n",
    "from sklearn.metrics import confusion_matrix, classification_report\n",
    "data = {\n",
    "    'Weather': ['Sunny', 'Sunny', 'Overcast', 'Rainy', 'Rainy', 'Rainy', 'Overcast', 'Sunny', 'Sunny', \n",
    "                'Rainy', 'Sunny', 'Overcast', 'Overcast', 'Overcast', 'Rainy'],\n",
    "    'Temperature': ['Hot', 'Hot', 'Hot', 'Mild', 'Cool', 'Cool', 'Mild', 'Mild', 'Cool', 'Mild', \n",
    "                    'Mild', 'Mild', 'Hot', 'Mild', 'Mild'],\n",
    "    'Play': ['No', 'No', 'Yes', 'Yes', 'Yes', 'No', 'Yes', 'No', 'Yes', 'Yes', 'Yes', 'Yes', 'Yes', 'Yes', 'No']\n",
    "}\n",
    "\n",
    "df = pd.DataFrame(data)\n",
    "label_encoder_weather = LabelEncoder()\n",
    "label_encoder_temp = LabelEncoder()\n",
    "label_encoder_play = LabelEncoder()\n",
    "df['Weather'] = label_encoder_weather.fit_transform(df['Weather'])\n",
    "df['Temperature'] = label_encoder_temp.fit_transform(df['Temperature'])\n",
    "df['Play'] = label_encoder_play.fit_transform(df['Play'])\n",
    "X = df[['Weather', 'Temperature']]\n",
    "y = df['Play']\n",
    "X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)\n",
    "knn = KNeighborsClassifier(n_neighbors=3)\n",
    "knn.fit(X_train, y_train)\n",
    "new_data = pd.DataFrame([[label_encoder_weather.transform(['Overcast'])[0], \n",
    "                          label_encoder_temp.transform(['Mild'])[0]]], \n",
    "                        columns=['Weather', 'Temperature'])\n",
    "predicted_play = knn.predict(new_data)\n",
    "y_pred = knn.predict(X_test)\n",
    "conf_matrix = confusion_matrix(y_test, y_pred)\n",
    "class_report = classification_report(y_test, y_pred, target_names=['No', 'Yes'])\n",
    "predicted_play_text = label_encoder_play.inverse_transform(predicted_play)[0]\n",
    "print(\"Prediction for Weather=Overcast and Temperature=Mild:\", predicted_play_text)\n",
    "print(\"\\nConfusion Matrix:\\n\", conf_matrix)\n",
    "print(\"\\nClassification Report:\\n\", class_report)\n",
    "\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 2,
   "id": "729ad196-935d-4730-8c71-ef040bbe25e2",
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "Distances from query instance to each training sample: [(4.0, 'Bad'), (5.0, 'Bad'), (3.0, 'Good'), (3.605551275463989, 'Good')]\n",
      "Nearest neighbors (k=3): [(3.0, 'Good'), (3.605551275463989, 'Good'), (4.0, 'Bad')]\n",
      "Predicted class for the query instance: Good\n"
     ]
    }
   ],
   "source": [
    "import numpy as np\n",
    "from collections import Counter\n",
    "training_data = {\n",
    "    'X1': [7, 7, 3, 1],\n",
    "    'X2': [7, 4, 4, 4],\n",
    "    'Y': ['Bad', 'Bad', 'Good', 'Good']\n",
    "}\n",
    "query_instance = (3, 7)\n",
    "distances = []\n",
    "for i in range(len(training_data['X1'])):\n",
    "    x1, x2 = training_data['X1'][i], training_data['X2'][i]\n",
    "    distance = np.sqrt((query_instance[0] - x1) ** 2 + (query_instance[1] - x2) ** 2)\n",
    "    distances.append((distance, training_data['Y'][i]))\n",
    "k = 3\n",
    "nearest_neighbors = sorted(distances)[:k]\n",
    "neighbor_classes = [neighbor[1] for neighbor in nearest_neighbors]\n",
    "predicted_class = Counter(neighbor_classes).most_common(1)[0][0]\n",
    "print(\"Distances from query instance to each training sample:\", distances)\n",
    "print(\"Nearest neighbors (k=3):\", nearest_neighbors)\n",
    "print(\"Predicted class for the query instance:\", predicted_class)\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "id": "12152f5b-c977-4b4e-986b-1bd6aca8c202",
   "metadata": {},
   "outputs": [],
   "source": []
  }
 ],
 "metadata": {
  "kernelspec": {
   "display_name": "Python 3 (ipykernel)",
   "language": "python",
   "name": "python3"
  },
  "language_info": {
   "codemirror_mode": {
    "name": "ipython",
    "version": 3
   },
   "file_extension": ".py",
   "mimetype": "text/x-python",
   "name": "python",
   "nbconvert_exporter": "python",
   "pygments_lexer": "ipython3",
   "version": "3.11.7"
  }
 },
 "nbformat": 4,
 "nbformat_minor": 5
}
