package com.example.modulaiztioncoursedemo.components


import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.core.domain.FilterOrder
import com.example.hero_domain.HeroAttribute
import com.example.hero_domain.HeroFilter
import com.example.herolist.R
import com.example.modulaiztioncoursedemo.ui.test.TAG_HERO_FILTER_AGILITY_CHECKBOX
import com.example.modulaiztioncoursedemo.ui.test.TAG_HERO_FILTER_ASC
import com.example.modulaiztioncoursedemo.ui.test.TAG_HERO_FILTER_DESC
import com.example.modulaiztioncoursedemo.ui.test.TAG_HERO_FILTER_HERO_CHECKBOX
import com.example.modulaiztioncoursedemo.ui.test.TAG_HERO_FILTER_INT_CHECKBOX
import com.example.modulaiztioncoursedemo.ui.test.TAG_HERO_FILTER_PROWINS_CHECKBOX
import com.example.modulaiztioncoursedemo.ui.test.TAG_HERO_FILTER_STENGTH_CHECKBOX
import com.example.modulaiztioncoursedemo.ui.test.TAG_HERO_FILTER_UNKNOWN_CHECKBOX

@OptIn(ExperimentalAnimationApi::class)
/*
*Some Jetpack Compose features are marked as experimental because they are still being tested and might change in future updates.
If you want to use those features, you must opt-in explicitly using the @OptIn annotation.
This tells the compiler that you understand the risks of using an unstable API.
* */
@Composable
fun HeroListFilterPopup(
    heroFilter: HeroFilter,
    onUpdateHeroFilter: (HeroFilter) -> Unit,
    attributeFilter: HeroAttribute = HeroAttribute.Unknown,
    onUpdateAttributeFilter: (HeroAttribute) -> Unit,
    onCloseDialog: () -> Unit
) {

    AppDialog(
        showDialog = true,
        onDismiss = onCloseDialog,
        modifier = Modifier,
        content = {

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                AppText(
                    text = "Filter",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                LazyColumn(
                    modifier = Modifier.weight(1f, fill = false)
                ) {
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            // Hero Filter
                            HeroFilterSelector(
                                filterOnHero = {
                                    onUpdateHeroFilter(HeroFilter.Hero())
                                },
                                isEnabled = heroFilter is HeroFilter.Hero,
                                order = if (heroFilter is HeroFilter.Hero) heroFilter.order else null,
                                orderDesc = {
                                    onUpdateHeroFilter(
                                        HeroFilter.Hero(
                                            order = FilterOrder.Descending
                                        )
                                    )
                                },
                                orderAsc = {
                                    onUpdateHeroFilter(
                                        HeroFilter.Hero(
                                            order = FilterOrder.Ascending
                                        )
                                    )
                                }
                            )
                            // Pro Win Rate Filter
                            // Pro Win Rate Filter
                            ProWinsFilterSelector(
                                filterOnProWins = {
                                    onUpdateHeroFilter(
                                        HeroFilter.ProWins()
                                    )
                                },
                                isEnabled = heroFilter is HeroFilter.ProWins,
                                order = if (heroFilter is HeroFilter.ProWins) heroFilter.order else null,
                                orderDesc = {
                                    onUpdateHeroFilter(
                                        HeroFilter.ProWins(
                                            order = FilterOrder.Descending
                                        )
                                    )
                                },
                                orderAsc = {
                                    onUpdateHeroFilter(
                                        HeroFilter.ProWins(
                                            order = FilterOrder.Ascending
                                        )
                                    )
                                },
                            )

                            Gap(height = 8)
                            MHorizontalDivider(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                thickness = 1.dp
                            )
                            Gap(height = 8)


                            // Primary Attribute Filter
                            PrimaryAttrFilterSelector(
                                removeFilterOnPrimaryAttr = {
                                    onUpdateAttributeFilter(HeroAttribute.Unknown)
                                },
                                attribute = attributeFilter,
                                onFilterStr = {
                                    onUpdateAttributeFilter(
                                        HeroAttribute.Strength
                                    )
                                },
                                onFilterAgi = {
                                    onUpdateAttributeFilter(
                                        HeroAttribute.Agility
                                    )
                                },
                                onFilterInt = {
                                    onUpdateAttributeFilter(
                                        HeroAttribute.Intelligence
                                    )
                                }
                            )
                        }
                    }
                }

                // Close Button
                AppIconButton(
                    icon = Icons.Default.Done,
                    modifier = Modifier.align(Alignment.End),
                    onClick = {
                        onCloseDialog()
                    },

                    )


            }

        }
    )
}


    /**
     * @param filterOnPrimaryAttr: Set the HeroFilter to 'PrimaryAttribute'
     * @param isEnabled: Is the PrimaryAttribute filter the selected 'HeroFilter'
     * @param attribute: Is the current attribute Strength, Agility or Intelligence?
     * @param orderStr: Set the order to Strength.
     * @param orderAgi: Set the order to Agility.
     * @param orderInt: Set the order to Intelligence.
     */
    @ExperimentalAnimationApi
    @Composable
    fun PrimaryAttrFilterSelector(
        removeFilterOnPrimaryAttr: () -> Unit,
        attribute: HeroAttribute,
        onFilterStr: () -> Unit,
        onFilterAgi: () -> Unit,
        onFilterInt: () -> Unit,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth(),
            ) {
                AppText(
                    text = stringResource(R.string.primary_attribute),
                    style = MaterialTheme.typography.headlineMedium // Use this instead of h3
                )
            }

            PrimaryAttrSelector(
                isStr = attribute is HeroAttribute.Strength,
                isAgi = attribute is HeroAttribute.Agility,
                isInt = attribute is HeroAttribute.Intelligence,
                onUpdateHeroFilterStr = {
                    onFilterStr()
                },
                onUpdateHeroFilterAgi = {
                    onFilterAgi()
                },
                onUpdateHeroFilterInt = {
                    onFilterInt()
                },
                onRemoveAttributeFilter = {
                    removeFilterOnPrimaryAttr()
                }
            )
        }
    }

    /**
     * @param filterOnProWins: Set the HeroFilter to 'ProWins'
     * @param isEnabled: Is the ProWins filter the selected 'HeroFilter'
     * @param order: Ascending or Descending?
     * @param orderDesc: Set the order to descending.
     * @param orderAsc: Set the order to ascending.
     */
    @ExperimentalAnimationApi
    @Composable
    fun ProWinsFilterSelector(
        filterOnProWins: () -> Unit,
        isEnabled: Boolean,
        order: FilterOrder? = null,
        orderDesc: () -> Unit,
        orderAsc: () -> Unit,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth()
                    .testTag(TAG_HERO_FILTER_PROWINS_CHECKBOX)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() }, // ✅ أفضل أداء
                        indication = null, // disable the highlight
                        enabled = true,
                        onClick = {
                            filterOnProWins()
                        },
                    ),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Checkbox(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .align(Alignment.CenterVertically),
                    checked = isEnabled,
                    onCheckedChange = {
                        filterOnProWins()
                    },
                    colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary) // ✅ NEW (Material 3)
                )
                AppText(
                    text = HeroFilter.ProWins().uiValue,
                    style = MaterialTheme.typography.headlineMedium // Use this instead of h3
                )
            }

            OrderSelector(
                descString = "100% - 0%",
                ascString = "0% - 100%",
                isEnabled = isEnabled,
                isDescSelected = isEnabled && order is FilterOrder.Descending,
                isAscSelected = isEnabled && order is FilterOrder.Ascending,
                onUpdateHeroFilterDesc = {
                    orderDesc()
                },
                onUpdateHeroFilterAsc = {
                    orderAsc()
                },
            )
        }
    }

    /**
     * @param filterOnHero: Set the HeroFilter to 'Hero'
     * @param isEnabled: Is the Hero filter the selected 'HeroFilter'
     * @param order: Ascending or Descending?
     * @param orderDesc: Set the order to descending.
     * @param orderAsc: Set the order to ascending.
     */
    @ExperimentalAnimationApi
    @Composable
    fun HeroFilterSelector(
        filterOnHero: () -> Unit,
        isEnabled: Boolean,
        order: FilterOrder? = null,
        orderDesc: () -> Unit,
        orderAsc: () -> Unit,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .testTag(TAG_HERO_FILTER_HERO_CHECKBOX)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() }, // ✅ أفضل أداء
                        indication = null, // disable the highlight
                        enabled = true,
                        onClick = {
                            filterOnHero()
                        },

                        ),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Checkbox(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .align(Alignment.CenterVertically),
                    checked = isEnabled,
                    onCheckedChange = {
                        filterOnHero()
                    },
                    colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary) // ✅ NEW (Material 3)
                )
                AppText(
                    text = HeroFilter.Hero().uiValue,
                    style = MaterialTheme.typography.headlineSmall // ✅ NEW (Material 3)
                )
            }

            OrderSelector(
                descString = "z -> a",
                ascString = "a -> z",
                isEnabled = isEnabled,
                isDescSelected = isEnabled && order is FilterOrder.Descending,
                isAscSelected = isEnabled && order is FilterOrder.Ascending,
                onUpdateHeroFilterDesc = {
                    orderDesc()
                },
                onUpdateHeroFilterAsc = {
                    orderAsc()
                },
            )
        }
    }

    /**
     * @param isStr: Is the selected attribute strength?
     * @param isAgi: Is the selected attribute Agility?
     * @param isInt: Is the selected attribute Intelligence?
     * @param onUpdateHeroFilterStr: Update the filter to Strength
     * @param onUpdateHeroFilterAgi: Update the filter to Agility
     * @param onUpdateHeroFilterInt: Update the filter to Intelligence
     */
    @SuppressLint("UnrememberedMutableInteractionSource")
    @ExperimentalAnimationApi
    @Composable
    fun PrimaryAttrSelector(
        isStr: Boolean = false,
        isAgi: Boolean = false,
        isInt: Boolean = false,
        onUpdateHeroFilterStr: () -> Unit,
        onUpdateHeroFilterAgi: () -> Unit,
        onUpdateHeroFilterInt: () -> Unit,
        onRemoveAttributeFilter: () -> Unit,
    ) {
        // Strength
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, bottom = 8.dp)
                .testTag(TAG_HERO_FILTER_STENGTH_CHECKBOX)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null, // disable the highlight
                    onClick = {
                        onUpdateHeroFilterStr()
                    },
                ),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Checkbox(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.CenterVertically),
                checked = isStr,
                onCheckedChange = {
                    onUpdateHeroFilterStr()
                },
                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary) // ✅ NEW (Material 3)
            )
            AppText(
                text = HeroAttribute.Strength.uiValue,
                style = MaterialTheme.typography.bodyLarge // ✅ NEW (Material 3)
            )
        }

        // Agility
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, bottom = 8.dp)
                .testTag(TAG_HERO_FILTER_AGILITY_CHECKBOX)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null, // disable the highlight
                    onClick = {
                        onUpdateHeroFilterAgi()
                    },
                ),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Checkbox(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.CenterVertically),
                checked = isAgi,
                onCheckedChange = {
                    onUpdateHeroFilterAgi()
                },
                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary) // ✅ NEW (Material 3)
            )
            AppText(
                text = HeroAttribute.Agility.uiValue,
                style = MaterialTheme.typography.bodyLarge // ✅ NEW (Material 3)
            )
        }

        // Intelligence
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, bottom = 8.dp)
                .testTag(TAG_HERO_FILTER_INT_CHECKBOX)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null, // disable the highlight
                    onClick = {
                        onUpdateHeroFilterInt()
                    },
                ),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Checkbox(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.CenterVertically),
                checked = isInt,
                onCheckedChange = {
                    onUpdateHeroFilterInt()
                },
                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary) // ✅ NEW (Material 3)
            )
            AppText(
                text = HeroAttribute.Intelligence.uiValue,
                style = MaterialTheme.typography.bodyLarge // ✅ NEW (Material 3)

            )
        }

        // No Filter on Attribute
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, bottom = 8.dp)
                .testTag(TAG_HERO_FILTER_UNKNOWN_CHECKBOX)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null, // disable the highlight
                    onClick = {
                        onRemoveAttributeFilter()
                    },
                ),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Checkbox(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.CenterVertically),
                checked = !isStr && !isAgi && !isInt,
                onCheckedChange = {
                    onRemoveAttributeFilter()
                },
                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary) // ✅ NEW (Material 3)
            )
            AppText(
                text = stringResource(R.string.none),
                style = MaterialTheme.typography.bodyLarge // ✅ NEW (Material 3)
            )
        }
    }

    /**
     * @param descString: String displayed in the "descending" checkbox
     * @param ascString: String displayed in the "ascending" checkbox
     * @param isEnabled: Is this HeroFilter currently the selected HeroFilter?
     * @param isDescSelected: Is the "descending" checkbox selected?
     * @param isAscSelected: Is the "ascending" checkbox selected?
     * @param onUpdateHeroFilterDesc: Set the filter to Descending.
     * @param onUpdateHeroFilterAsc: Set the filter to Ascending.
     */
    @ExperimentalAnimationApi
    @Composable
    fun OrderSelector(
        descString: String,
        ascString: String,
        isEnabled: Boolean,
        isDescSelected: Boolean,
        isAscSelected: Boolean,
        onUpdateHeroFilterDesc: () -> Unit,
        onUpdateHeroFilterAsc: () -> Unit,
    ) {
        // Descending Order
        AnimatedVisibility(visible = isEnabled) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, bottom = 8.dp)
                    .testTag(TAG_HERO_FILTER_DESC)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() }, // ✅ أفضل أداء
                        indication = null, // disable the highlight
                        enabled = isEnabled,
                        onClick = {
                            onUpdateHeroFilterDesc()
                        },
                    ),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Checkbox(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .align(Alignment.CenterVertically),
                    enabled = isEnabled,
                    checked = isEnabled && isDescSelected,
                    onCheckedChange = {
                        onUpdateHeroFilterDesc()
                    },
                    colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary) // ✅ NEW (Material 3)
                )
                AppText(
                    text = descString,
                    style = MaterialTheme.typography.bodyLarge // ✅ NEW (Material 3)
                )
            }
        }

        // Ascending Order
        AnimatedVisibility(visible = isEnabled) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, bottom = 8.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() }, // ✅ أفضل أداء
                        indication = null, // disable the highlight
                        enabled = isEnabled,
                        onClick = {
                            onUpdateHeroFilterAsc()
                        },
                    ),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Checkbox(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .testTag(TAG_HERO_FILTER_ASC)
                        .align(Alignment.CenterVertically),
                    enabled = isEnabled,
                    checked = isEnabled && isAscSelected,
                    onCheckedChange = {
                        onUpdateHeroFilterAsc()
                    },
                    colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary) // ✅ NEW (Material 3)
                )
                AppText(
                    text = ascString,
                    style = MaterialTheme.typography.bodyLarge // ✅ NEW (Material 3)
                )
            }
        }
    }


